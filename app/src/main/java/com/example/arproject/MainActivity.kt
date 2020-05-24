package com.example.arproject

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var arFragment: ArFragment? = null
    var bearRenderable: ModelRenderable? = null
    var cowRenderable: ModelRenderable? = null
    var dogRenderable: ModelRenderable? = null
    var elephantRenderable: ModelRenderable? = null
    var ferretRenderable: ModelRenderable? = null
    var hippoRenderable: ModelRenderable? = null
    var koalaRenderable: ModelRenderable? = null
    var bear: ImageView? = null
    var cow: ImageView? = null
    var dog: ImageView? = null
    var elephant: ImageView? = null
    var ferret: ImageView? = null
    var hippo: ImageView? = null
    var koala: ImageView? = null
    lateinit var arrayView: Array<View>
    var selected = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arFragment =
            getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment) as ArFragment
        bear = findViewById(R.id.bear)
        cow = findViewById(R.id.cow)
        dog = findViewById(R.id.dog)
        elephant = findViewById(R.id.elephant)
        ferret = findViewById(R.id.ferret)
        hippo = findViewById(R.id.hippo)
        koala = findViewById(R.id.koala)
        setArrayView()
        setClickListener()

        setupModel()

        arFragment!!.setOnTapArPlaneListener(BaseArFragment.OnTapArPlaneListener() { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->

            run {
                val anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                anchorNode.setParent(arFragment?.getArSceneView()?.getScene())
                createModel(anchorNode, selected)
            }
        })


    }

    private fun setupModel() {
        ModelRenderable.builder()
            .setSource(this, R.raw.bear)
            .build().thenAccept { renderable: ModelRenderable? -> bearRenderable = renderable }
        ModelRenderable.builder()
            .setSource(this, R.raw.cow)
            .build().thenAccept { renderable: ModelRenderable? -> cowRenderable = renderable }
        ModelRenderable.builder()
            .setSource(this, R.raw.dog)
            .build().thenAccept { renderable: ModelRenderable? -> dogRenderable = renderable }
        ModelRenderable.builder()
            .setSource(this, R.raw.elephant)
            .build().thenAccept { renderable: ModelRenderable? -> elephantRenderable = renderable }
        ModelRenderable.builder()
            .setSource(this, R.raw.ferret)
            .build().thenAccept { renderable: ModelRenderable? -> ferretRenderable = renderable }
        ModelRenderable.builder()
            .setSource(this, R.raw.hippopotamus)
            .build().thenAccept { renderable: ModelRenderable? -> hippoRenderable = renderable }
        ModelRenderable.builder()
            .setSource(this, R.raw.koala_bear)
            .build().thenAccept { renderable: ModelRenderable? -> koalaRenderable = renderable }
    }

    fun createModel(anchorNode: AnchorNode, selected: Int) {
        if (selected == 1) {
            var bear = TransformableNode(arFragment!!.transformationSystem)
            bear.setParent(anchorNode)
            bear.setRenderable(bearRenderable)
            bear.select()

        }

    }


    fun setClickListener() {
        for (i in 0 until arrayView.size)
            arrayView[i].setOnClickListener(this)
    }

    fun setArrayView() {
        arrayView = arrayOf<ImageView?>(bear, cow, dog, elephant, ferret, hippo, koala) as Array<View>
    }

    override fun onClick(v: View) {
        TODO("Not yet implemented")
    }
}


