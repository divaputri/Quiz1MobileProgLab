package com.example.quizlab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.quizlab1.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity{

    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String url = "https://reqres.in/api/users";
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getStringRequest(url));

        users = new ArrayList<User>();

        prepareListView();
    }

    private StringRequest getStringRequest(String url){
        return new StringRequest(Request.Method.GET, url, (response) -> {
            try{
                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("data");

                for (int i = 0; i < 5; i++) {
                    JSONObject user = array.getJSONObject(i);
                    users.add(new User(user.getString("first_name"), user.getString("last_name"), user.getString("avatar")));

                    prepareListView();
                }

            } catch (JSONException e){
                e.printStackTrace();
            }

        }, (error) -> {

        });
    }

    private void prepareListView() {
        RecyclerView rv = findViewById(R.id.list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DataModelAdapter dataModelAdapter = new DataModelAdapter(users);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dataModelAdapter);
    }
}

class DataModelAdapter extends RecyclerView.Adapter<DataModelViewHolder> {

    ArrayList<User> dataModels;

    public DataModelAdapter(ArrayList<User> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_card_view, parent, false);

        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        User model = dataModels.get(position);

        holder.firstName.setText(model.getFirstname());
        holder.lastName.setText(model.getLastname());

        Glide.with(holder.parent.getContext())
                .load(dataModels.get(position).getAvatar())
                .circleCrop()
                .into(holder.avatarDisplay);

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}

class DataModelViewHolder extends RecyclerView.ViewHolder {
    View parent;
    TextView firstName;
    TextView lastName;
    ImageView avatarDisplay;

    public DataModelViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        firstName = itemView.findViewById(R.id.first_name);
        lastName = itemView.findViewById(R.id.last_name);
        avatarDisplay = itemView.findViewById(R.id.image_view);
    }
}