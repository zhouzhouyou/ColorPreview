package edu.colorpreview.network;

import edu.colorpreview.model.Bookmark;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import edu.colorpreview.model.Design;
import java.util.List;

public interface ApiInterface {
    @PUT("design/createBookmark")
    Call<Integer> createBookMark(
            @Query("did") Integer did,
            @Query("uid") Integer uid
    );

    @PUT("design/createDesign")
    Call<Integer> createDesign(
            @Query("name") String name,
            @Query("p") String p,
            @Query("pd") String pd,
            @Query("pl") String pl,
            @Query("s") String s,
            @Query("sd") String sd,
            @Query("sl") String sl,
            @Query("tp") String tp,
            @Query("ts") String ts,
            @Query("uid") Integer uid
    );

    @DELETE("design/deleteBookmark")
    Call<Integer> deleteBookmark(
            @Query("did") Integer did,
            @Query("uid") Integer uid
    );

    @DELETE("design/deleteDesign")
    Call<Integer> deleteDesign(
            @Query("id") Integer id
    );

    @POST("design/getAllDesigns")
    Call<List<Design>> getAllDesigns();

    @POST("design/getAllUserBookmark")
    Call<List<Design>> getAllUserBookmark(
            @Query("uid") Integer uid
    );

    @POST("design/getDesignById")
    Call<Design> getDesignById(
            @Query("id") Integer id
    );

    @POST("design/getUserDesigns")
    Call<List<Design>> getUserDesigns(
            @Query("uid") Integer uid
    );
}
