# MaterailDesign
[参考网站](https://material.io/)
## Toolbar

### 样式设置
最新版本中,Activity必须是AppCompatActivity的子类,这就意味着Activity的theme 必须为 Theme.AppCompat 
而要使用Toolbar代替原有的ActionBar,则要进行下面的设置
1. 采用Theme.AppCompat 中的NoActionBar 设置主题
2. `app:theme` 设置toolbar的样式 继承自'ThemeOverlay.AppCompat'
3. `app:popupTheme` 设置toolbar菜单点击弹出框的样式

### 显示
添加Toolbar 需要在Activity的布局文件中加入 `android.support.v7.widget.Toolbar`
由于几乎每一个Activity都需要一个Toolbar 所以建议将toolbar的layout文件独立出来`app_bar.xml`
```xml
<android.support.v7.widget.Toolbar
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/colorPrimary"
    app:theme="@style/ToolbarTheme"
    app:popupTheme="@style/ThemeOverlay.AppCompat.Dark">

</android.support.v7.widget.Toolbar>
```
在Activity中将toolbar设置为actionbar之后,toolbar就能正常显示了
```java
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar = (Toolbar) findViewById(R.id.app_bar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("MainActivity");
```
### 功能
1. 返回 需要在manifest文件中设置Activity的父Activity 并设置开启actionbar的home键返回功能
```xml
 <meta-data android:name="android.support.PARENT_ACTIVITY" android:value=".MainActivity"/>
```
```java
     getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
```

### Tips
1. 直接为toolbar设置title不起作用,必须在toolbar设置为actionbar之后 为actionbar设置title才行
2. title的位置比较固定,如果要自定义title的位置,可以在toolbar布局中加入自己的布局,并将actionbar设置为不显示title
```java
    getSupportActionBar().setDisplayShowTitleEnabled(false);
```\