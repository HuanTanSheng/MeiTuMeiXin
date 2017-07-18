package com.huantansheng.meitumeixin.main.fragment.imagelist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.huantansheng.meitumeixin.R
import com.huantansheng.meitumeixin.base.MyGlide
import com.huantansheng.meitumeixin.constants.Constants
import com.huantansheng.meitumeixin.listener.CommonListener
import com.huantansheng.meitumeixin.main.fragment.imagelist.contract.LikedContract
import com.huantansheng.meitumeixin.photo.PhotoActivity
import com.huantansheng.meitumeixin.utiles.ScreenUtil
import org.jetbrains.anko.support.v4.startActivityForResult

/**
 * Created by huan on 2017/7/9.
 */
class ImageListFragment : Fragment(), LikedContract.IView, SwipeRefreshLayout.OnRefreshListener, CommonListener.OnPhotoClickListener {
    override fun onPhotoClick(position: Int) {
        startActivityForResult<PhotoActivity>(CODE_REQUEST, PhotoActivity.INTENT_DATA to likedList, PhotoActivity.INTENT_POSITION to position)
    }

    private var type = 0
    private lateinit var likedAdapter: LikedRecyclerViewAdapter
    private lateinit var layoutManager: GridLayoutManager
    private var likedList = ArrayList<String>()
    val CODE_REQUEST = 101

    private lateinit var rvLiked: RecyclerView
    private lateinit var glide : RequestManager

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
        layoutManager = GridLayoutManager(context, spanCount)
        val screenWidth = ScreenUtil.getWidth(context)
        likedAdapter = LikedRecyclerViewAdapter(glide,likedList, LayoutInflater.from(activity), screenWidth, spanCount, this)

        rvLiked.layoutManager = layoutManager
        rvLiked.adapter = likedAdapter
        rvLiked.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    var position = layoutManager.findLastVisibleItemPosition()
                    if (position == likedList.size - 1) {
                        loadMore()
                    }
                }
            }
        })
    }

    fun initData() {

        likedList.add("http://pic67.nipic.com/file/20150514/21036787_181947848862_2.jpg")
        likedList.add("http://pic4.nipic.com/20091128/1951702_000139834348_2.jpg")
        likedList.add("http://pic.58pic.com/58pic/11/09/64/39i58PICmgE.jpg")
        likedList.add("http://b.hiphotos.baidu.com/zhidao/pic/item/1f178a82b9014a90e7eb9d17ac773912b21bee47.jpg")
        likedList.add("http://c.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=9742b125133853438c9a8f25a6239c48/29381f30e924b8990b693b716d061d950a7bf626.jpg")
        likedList.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg")

        likedAdapter.notifyDataSetChanged()
    }

    //***************************************Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments.getInt(TYPE)
        glide = Glide.with(this).applyDefaultRequestOptions(MyGlide.getOptions())
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