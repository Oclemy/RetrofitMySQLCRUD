package info.camposha.retrofitmysqlcrud.Retrofit;

/**
 * Let's define our imports
 */
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Let's Create an interface
 */
public interface RestApi {

    /**
     * This method will allow us perform a HTTP GET request to the specified url
     * .The response will be a ResponseModel object.
     */
    @GET("index.php")
    Call<ResponseModel> retrieve();

    /**
     * This method will allow us perform a HTTP POST request to the specified url.In the process
     * we will insert data to mysql and return a ResponseModel object
     */
    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseModel> insertData(@Field("action") String action,
                                   @Field("name") String name,
                                   @Field("description") String description,
                                   @Field("galaxy") String galaxy,
                                   @Field("star") String star,
                                   @Field("dob") String dob,
                                   @Field("died") String died);

    /**
     * This method will allow us update our mysql data by making a HTTP POST request.
     * After that
     * we will receive a ResponseModel model object
     */
    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseModel> updateData(@Field("action") String action,
                                   @Field("id") String id,
                                   @Field("name") String name,
                                   @Field("description") String description,
                                   @Field("galaxy") String galaxy,
                                   @Field("star") String star,
                                   @Field("dob") String dob,
                                   @Field("died") String died);

    /**
     * This method will allow us to search our data while paginating the search results. We
     * specify the search and pagination parameters as fields.
     */
    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseModel> search(@Field("action") String action,
                               @Field("query") String query,
                               @Field("start") String start,
                               @Field("limit") String limit);

    /**
     * This method will aloow us to remove or delete from database the row with the
     *  specified
     * id.
     */
    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseModel> remove(@Field("action") String action, @Field("id") String id);
}
//end
