package jp.techacademy.chiaki.hata.apiapp

interface FragmentCallback {
    fun onClickItem(shop: Shop)

    fun onAddFavorite(shop: Shop)

    fun onDeleteFavorite(id: String)

}