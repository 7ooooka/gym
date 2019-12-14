package my.gym;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import my.gym.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


public class workoutsActivity extends Activity {
    private RecyclerView mRecyclerView;
   private static final String image_url = "http://fitnessgymapp.000webhostapp.com/ionic/ionic/backend/images/";
    private List<workoutClass> workoutsList;
    private View mRecyclerrView;
    private View mProgressView;
    private TextView trainerfit;
    private TextView exercisesFooter;
    private TextView workoutsFooter;
    private TextView storeFooter;
private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        mRecyclerrView = findViewById(R.id.content_main);
        mProgressView = findViewById(R.id.login_progress);
        mRecyclerView = findViewById(R.id.workouts);

        workoutsList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        trainerfit = findViewById(R.id.trainerfit);
        exercisesFooter = findViewById(R.id.exercises);
        workoutsFooter = findViewById(R.id.workoutsFooter);
        storeFooter = findViewById(R.id.store);
        trainerfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(workoutsActivity.this,mainPage.class);
                startActivity(i);

            }
        });
        exercisesFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(workoutsActivity.this,ExercisesActivity.class);
                startActivity(i);

            }
        });

        storeFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(workoutsActivity.this,StoreActivity.class);
                startActivity(i);

            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.workoutURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
               API api = retrofit.create(API.class);
        Call<List<workoutClass>> call = api.getworkouts();
        showProgress(true);
         call.enqueue(new Callback<List<workoutClass>>() {
             @Override
             public void onResponse(retrofit.Response<List<workoutClass>> response, Retrofit retrofit) {
                 try {
                     if(response.body()!=null)
                     {
                     List<workoutClass> works = response.body();

                     for (int i = 0; i < works.size(); i++) {
                         workoutClass workoutClass = new workoutClass();
                         workoutClass.setWorkout_id(works.get(i).getWorkout_id());
                         workoutClass.setWorkout_title(works.get(i).getWorkout_title());
                         workoutClass.setWorkout_description(works.get(i).getWorkout_description());
                         workoutClass.setWorkout_cover(works.get(i).getWorkout_cover());
                         workoutClass.setWorkout_diffculty(works.get(i).getWorkout_diffculty());
                         workoutClass.setWorkout_duration(works.get(i).getWorkout_duration());
                         workoutClass.setWorkout_goals(works.get(i).getWorkout_goals());
                         workoutsList.add(workoutClass);
                     }

                     }
                     else
                     {
                         Toast.makeText(getApplicationContext(), "workout does not exist", Toast.LENGTH_SHORT).show();
                     }
                 } catch (Exception e) {
                     Log.d("onResponse", "There is an error");
                     e.printStackTrace();
                 }
                 showProgress(false);
             }
             @Override
             public void onFailure(Throwable t) {
                  t.printStackTrace();
                 showProgress(false);
             }
         });


        mRecyclerView.setLayoutManager(new LinearLayoutManager(workoutsActivity.this));
        ActivityAdapter adabter = new ActivityAdapter(workoutsList);
        mRecyclerView.setAdapter(adabter);


      //  Toast.makeText(getApplicationContext(),String.valueOf(workoutsList.size()),Toast.LENGTH_LONG).show();



    }


    public class ActivityHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView tiltle;
        private TextView duration;
        private TextView difficulty;
        private TextView goal;


        public ActivityHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.workoutPhoto);
            tiltle = itemView.findViewById(R.id.productPrice);
            duration = itemView.findViewById(R.id.productShipping);
            difficulty = itemView.findViewById(R.id.productSave);
            goal = itemView.findViewById(R.id.productTitle);

        }

        public void bindworkout(workoutClass activity) {


          //  Toast.makeText(getApplicationContext(),  activity.getWorkout_id() , Toast.LENGTH_SHORT).show();
String title = activity.getWorkout_title().replace("&amp;","&");
             tiltle.setText(title);
            duration.setText(activity.getWorkout_duration());
            difficulty.setText(activity.getWorkout_diffculty());
            goal.setText(activity.getWorkout_goals());

            String workou_image_url = image_url + activity.getWorkout_cover();

            /* *************************Request an image****************************** */

            ImageRequest imageRequest = new ImageRequest(workou_image_url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                 image.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.FIT_XY, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"something went Wrong",
                            Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            });

              mRequestQueue.add(imageRequest);

            /* *************************Request an image****************************** */

        }


    }

    public class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {

        private List<workoutClass> workouts;
        public ActivityAdapter(List<workoutClass> workoutss) {
            workouts = workoutss;
        }

        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(workoutsActivity.this);
            View v = inflater.inflate(R.layout.workout, parent, false);

            return new ActivityHolder(v);
        }

        @Override
        public void onBindViewHolder(ActivityHolder holder, int position) {
            final workoutClass work = workouts.get(position);
            holder.bindworkout(work);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent i = workoutDetailsActivity.newIntent(getApplicationContext(),work);
                    startActivity(i);


                }
            });
        }

        @Override
        public int getItemCount() {
            return workouts.size();
        }
    }




    /************************************************************/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRecyclerrView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRecyclerrView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRecyclerrView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRecyclerrView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /*************************************************************/














}


