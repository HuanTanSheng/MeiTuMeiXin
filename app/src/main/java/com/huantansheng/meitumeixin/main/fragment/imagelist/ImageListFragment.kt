package com.huantansheng.meitumeixin.main.fragment.imagelist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.huantansheng.meitumeixin.R
import com.huantansheng.meitumeixin.constants.Constants
import com.huantansheng.meitumeixin.entity.Photo
import com.huantansheng.meitumeixin.entity.Photos
import com.huantansheng.meitumeixin.listener.CommonListener
import com.huantansheng.meitumeixin.main.fragment.imagelist.contract.LikedContract
import com.huantansheng.meitumeixin.photo.PhotoActivity
import com.huantansheng.meitumeixin.retrofit.Api
import com.huantansheng.meitumeixin.utiles.ScreenUtil
import org.jetbrains.anko.support.v4.startActivityForResult
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by huan on 2017/7/9.
 */
class ImageListFragment : Fragment(), LikedContract.IView, SwipeRefreshLayout.OnRefreshListener, CommonListener.OnPhotoClickListener {
    override fun onPhotoClick(position: Int) {
        startActivityForResult<PhotoActivity>(CODE_REQUEST, PhotoActivity.INTENT_DATA to likedList, PhotoActivity.INTENT_POSITION to position)
    }

    private var type = 0
    private lateinit var likedAdapter: LikedRecyclerViewAdapter
    private lateinit var layoutManager: StaggeredGridLayoutManager
    private var likedList = ArrayList<Photo>()
    val CODE_REQUEST = 101

    private lateinit var rvLiked: RecyclerView
    private lateinit var glide: RequestManager

    //**********************************************ImageListFragment
    companion object {
        val TYPE = "type"

        fun newInstance(type: Int): ImageListFragment {
            var imageListFragment = ImageListFragment()
            var args = Bundle()
            args.putInt(TYPE, type)
            imageListFragment.arguments = args
            return imageListFragment
        }
    }

    fun initView() {
        var spanCount = 0
        when (type) {
            Constants.IMAGE_LIST_LIKED -> {
                spanCount = Constants.GRIDLAYOUTMANAGER_SPANCOUNT_LIKED
            }
            Constants.IMAGE_LIST_FIND -> {
                spanCount = Constants.GRIDLAYOUTMANAGER_SPANCOUNT_FIND
            }
            Constants.IMAGE_LIST_UNLIKED -> {
                spanCount = Constants.GRIDLAYOUTMANAGER_SPANCOUNT_UNLIKED
            }
        }
        layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        val screenWidth = ScreenUtil.getWidth(context)
        likedAdapter = LikedRecyclerViewAdapter(glide, likedList, LayoutInflater.from(activity), screenWidth, spanCount, this)

        rvLiked.layoutManager = layoutManager
        rvLiked.adapter = likedAdapter
        rvLiked.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    var count = layoutManager.spanCount
                    var lastpositions = IntArray(count)
                    layoutManager.findLastVisibleItemPositions(lastpositions)
                    var position = lastpositions.max()
                    if (position == likedList.size - 1) {
                        loadMore()
                    }
                }
            }
        })
    }


    fun initData() {

        Api.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Photos>() {
                    override fun onNext(t: Photos?) {
                        if (t != null) {
                            likedList.addAll(t.photos)
                            likedAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {

                    }

                })

    }

    //***************************************Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments.getInt(TYPE)
        glide = Glide.with(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_main_liked, container, false)
        rvLiked = view.findViewById<RecyclerView>(R.id.rv_liked)
        initView()
        initData()
        return view
    }


    //****************************************SwipeRefreshLayout.OnRefreshListener
    override fun onRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //************************************LikedContract.IView
    override fun loadMore() {
        initData()
    }

}