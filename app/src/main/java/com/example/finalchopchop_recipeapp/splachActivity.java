package com.example.finalchopchop_recipeapp;

public class splachActivity {
    //variables

    Animation top,bottom;
    ImageView logo;
    ImageView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splach_screen);

        //Animation
        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom =AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        logo =findViewById(R.id.imageView4);
        appName =findViewById(R.id.imageView3);


        logo.setAnimation(top);
        appName.setAnimation(bottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);

    }
}
