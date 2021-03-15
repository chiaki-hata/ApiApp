package jp.techacademy.chiaki.hata.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val shop = intent.getSerializableExtra(KEY_URL) as Shop
        val url2 = if (shop.couponUrls.sp.isNotEmpty()) shop.couponUrls.sp else shop.couponUrls.pc
        webView.loadUrl(url2)

        val isFavorite = FavoriteShop.findBy(shop.id) != null

        favorite.apply {
            setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)
            setOnClickListener {
                if (isFavorite) {
                    FavoriteShop.delete(shop.id)
                    favorite.setImageResource(R.drawable.ic_star_border)
                    favorite.visibility = View.VISIBLE
                    favorite2.visibility = View.GONE

                } else {
                    FavoriteShop.insert(FavoriteShop().apply {
                        id = shop.id
                        name = shop.name
                        imageUrl = shop.logoImage
                        url = if (shop.couponUrls.sp.isNotEmpty()) shop.couponUrls.sp else shop.couponUrls.pc
                        address = shop.address

                    })
                    favorite.setImageResource(R.drawable.ic_star)
                    favorite.visibility = View.GONE
                    favorite2.visibility = View.VISIBLE
                }
            }

        }
    }

    companion object {
        private const val KEY_URL = "key_url"
        fun start(activity: Activity, shop: Shop) {
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(KEY_URL, shop))
        }
    }
}

