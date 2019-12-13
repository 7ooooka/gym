package my.gym;

import java.util.List;

import retrofit.http.GET;


public interface API {

     String workoutURL = "http://fitnessgymapp.000webhostapp.com/ionic/ionic/backend/controller/";
     String exercisesJsonURL = "http://fitnessgymapp.000webhostapp.com/ionic/ionic/backend/controller/";
     String DietsURL = "http://fitnessgymapp.000webhostapp.com/ionic/ionic/backend/controller/";
     @GET("WorkoutJson.php")
     retrofit.Call<List<workoutClass>> getworkouts();


     @GET("exercisesJson.php")
     retrofit.Call<List<exercisesJson>> getworkouts_exercises();

     @GET("DietsJson.php")
     retrofit.Call<List<DietPlan>> getDiets();

     @GET("productsJson.php")
     retrofit.Call<List<product>> getProducts();

     @GET("AllExercisesJson.php")
     retrofit.Call<List<exercisesJson>> getAllExercises();

     @GET("exerciseId.php")
     retrofit.Call<List<workouts_exercises>> getAllExerciseid();

     @GET("categories_productsJson.php")
     retrofit.Call<List<category_product>> getcategories_products();

     @GET("equipmentsJson.php")
     retrofit.Call<List<equipment>> getEquipments();

     @GET("bodypartsJson.php")
     retrofit.Call<List<bodyParts>> getBodyBarts();

     @GET("settingJson.php")
     retrofit.Call<List<setting>> getSettings();



}
