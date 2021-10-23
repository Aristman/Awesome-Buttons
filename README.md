# Awesome-Buttons

Awesome-Buttons - библиотека красивых анимированных кнопок для Android приложений

## Установка

Добавьте в файл <code>build.gradle</code>, который находится в <code>app</code> модуле: 

    implementation 'io.github.kiberneticworm:awesome-buttons:1.0.1'

И синхронизируйте проект.

Теперь вы можете юзать кнопки с классной анимацией!

## TextButton

<img src="https://github.com/KiberneticWorm/Awesome-Buttons/blob/master/gifs/text_button_animations.gif" style="width: 50%" />

Пример кода:

    <ru.freeit.awesomebuttons.text.TextButton
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="16dp"
        app:borderWidth="1dp"
        app:borderRadius="20dp"
        app:bgColor="@android:color/transparent"
        app:animation="left_to_right_animator"
        android:text="left to right animation"
        android:layout_margin="16dp" />

    <ru.freeit.awesomebuttons.text.TextButton
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="right to left animation"
        app:animation="right_to_left_animator"
        android:padding="16dp"
        android:layout_margin="16dp" />

    <ru.freeit.awesomebuttons.text.TextButton
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:animation="center_vertical_animator"
        android:padding="16dp"
        android:text="center vertical animation"
        android:layout_margin="16dp" />

    <ru.freeit.awesomebuttons.text.TextButton
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:animation="center_horizontal_animator"
        android:text="center horizontal animation"
        android:padding="16dp"
        android:layout_margin="16dp" />

    <ru.freeit.awesomebuttons.text.TextButton
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:animation="diagonal_bottom_left_top_right_animator"
        android:text="diagonal animation"
        android:padding="16dp"
        android:layout_margin="16dp" />

    <ru.freeit.awesomebuttons.text.TextButton
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:animation="diagonal_top_left_bottom_right_animator"
        android:text="diagonal animation"
        android:padding="16dp"
        android:layout_margin="16dp" />
    

## IconButton

<img src="https://github.com/KiberneticWorm/Awesome-Buttons/blob/master/gifs/icon_button.gif" style="width: 50%" />

Пример кода:

    <ru.freeit.awesomebuttons.icon.IconButton
        app:srcCompat="@drawable/ic_baseline_check_24"
        android:padding="16dp"
        app:bgColor="@color/teal_200"
        app:rippleColor="@color/teal_700"
        app:cornerRadius="10dp"
        android:layout_width="100dp"
        android:layout_height="100dp" />
    
    <ru.freeit.awesomebuttons.icon.IconButton
        app:srcCompat="@drawable/ic_baseline_close_24"
        android:padding="16dp"
        app:bgColor="@android:color/holo_red_light"
        app:rippleColor="@android:color/holo_red_dark"
        android:layout_marginTop="16dp"
        app:cornerRadius="100dp"
        android:layout_width="100dp"
        android:layout_height="100dp" />

    <ru.freeit.awesomebuttons.icon.IconButton
        app:srcCompat="@drawable/ic_baseline_edit_24"
        android:padding="24dp"
        app:bgColor="@color/purple_200"
        app:rippleColor="@color/purple_500"
        app:cornerRadius="25dp"
        android:layout_marginTop="16dp"
        android:layout_width="100dp"
        android:layout_height="100dp" />
