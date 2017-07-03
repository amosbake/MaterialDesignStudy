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
```

## DrawerLayout+NavigationView
DrawerLayout+NavigationView 是官方提供的一套侧滑菜单的解决方案,其中NavigationView是边缘滑出的菜单
### 引入
DrawerLayout 一般作为Activity的根布局,主内容一般作为第一个子view,`NavigationView`作为最后一个.以实现在内容页上面显示的效果.如果需要使用`CoordinatorLayout+Toolbar`的组合,一般来说布局是这样的
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:clickable="true"
    android:focusableInTouchMode="true">
  <android.support.design.widget.CoordinatorLayout
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context="io.mopel.materialdesignstudy.MainActivity">
    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize">
      <include
          android:id="@+id/app_bar"
          layout="@layout/app_bar"/>
    </android.support.design.widget.AppBarLayout>
    <android.support.constraint.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="?attr/actionBarSize">
      <FrameLayout
          android:id="@+id/content_main"
          android:layout_width="368dp"
          android:layout_height="495dp"
          app:layout_constraintTop_toTopOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          tools:layout_editor_absoluteX="8dp">
        <fragment
            android:layout_width="match_parent"
            android:layout_height="match_parent"
        android:name="io.mopel.materialdesignstudy.NavigationFragment"/>
          </FrameLayout>
    </android.support.constraint.ConstraintLayout>
  </android.support.design.widget.CoordinatorLayout>
  <android.support.design.widget.NavigationView
      android:id="@+id/navigation_view"
      android:layout_width="wrap_content"
      android:layout_height="match_parent"
      android:layout_gravity="start"
      android:background="@android:color/white"
      app:headerLayout="@layout/nav_header"
      app:menu="@menu/navigationdrawer_main" />
</android.support.v4.widget.DrawerLayout>
```
### 重要属性
`NavigationView`的`headLayout`,`menu` 分别指的是侧滑菜单的上部和菜单栏
要使菜单栏中的图标保持原图的颜色,设置方法如`navigationView.setItemIconTintList(null);`
获取headLayout的方法是` drawerHeaderView = navigationView.getHeaderView(0);`
获取menu的点击事件方法为`navigationView.setNavigationItemSelectedListener(this);`
而想要使icon图标可点击切换侧滑菜单的显示与隐藏,则需要为其添加监听器,具体代码如下
```java
 ActionBarDrawerToggle actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
            R.string.drawer_close){
          @Override public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
          }

          @Override public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
          }
        };
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
```
