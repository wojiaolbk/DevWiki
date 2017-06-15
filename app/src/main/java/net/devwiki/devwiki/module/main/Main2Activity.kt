package net.devwiki.devwiki.module.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import net.devwiki.devwiki.R

class Main2Activity : AppCompatActivity(), KotlinInterface {
    override fun get() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}
