package com.example.marvelheroes.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.PrimaryKey;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.marvelheroes.R;
import com.example.marvelheroes.data.Hero;
import com.example.marvelheroes.data.HeroRepository;

public class AddHeroActivity extends AppCompatActivity {

    private ImageView addImage;
    private EditText addName, addDesc;
    private Button saveButton;
    private Uri imageUri;

    private HeroRepository repository;

    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);

        addImage = findViewById(R.id.add_image);
        addName = findViewById(R.id.add_name);
        addDesc = findViewById(R.id.add_desc);
        saveButton = findViewById(R.id.save);

        repository = new HeroRepository(getApplication());

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addName.getText().toString();
                String desc = addDesc.getText().toString();
                repository.insert(new Hero(name, desc, imageUri.toString()));
                finish();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if(data != null){
                imageUri = data.getData();
                loadImage(addImage, imageUri);
            }
        }
    }

    private void loadImage(ImageView imageView, Uri uri){
        Glide.with(imageView)
                .load(uri)
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .into(imageView);
    }
}
