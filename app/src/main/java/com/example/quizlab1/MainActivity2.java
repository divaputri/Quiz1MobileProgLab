package com.example.quizlab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quizlab1.Models.DataModel;
import com.example.quizlab1.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

                users.add(new User("first_name", "last_name", "Avatar"));

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

        holder.titleText.setText(model.getTitle());
        holder.descriptionText.setText(model.getDescription());


    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}

class DataModelViewHolder extends RecyclerView.ViewHolder {
    View parent;
    TextView titleText;
    TextView descriptionText;
    ImageView avatarDisplay;

    public DataModelViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        titleText = itemView.findViewById(R.id.title_text);
        descriptionText = itemView.findViewById(R.id.description_text);
        avatarDisplay = itemView.findViewById(R.id.image_view);
    }
}