<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="presenter"
            type="${relativePackage}.${presenterClass}"/>
    </data>

    <LinearLayout xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context="${relativePackage}.${className}">

        <!-- TODO: Update blank fragment layout -->
    	<TextView
        	android:layout_width="match_parent"
        	android:layout_height="match_parent"
        	android:text="@string/hello_blank_fragment" />
    </LinearLayout>

</layout>