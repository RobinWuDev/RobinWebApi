package com.robinwu.api.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Robin on 16/6/6.
 */
public class TestData {
    public static Album getTempAlbum() {
        Album album = new Album();
        album.setId(10);
        album.setName("Album1");
        album.setYear("1990");
        return album;
    }

    public static Music getTempMusic() {
        Music music = new Music();
        music.setId(10);
        music.setName("Music1");
        music.setAlbumId(10);
        return music;
    }

    public static MingYanCategory getTempMingYanCategory() {
        MingYanCategory category = new MingYanCategory();
        category.setId(10);
        category.setName("MingYanCategory1");
        return category;
    }

    public static MingYan getTempMingYan() {
        MingYan mingYan = new MingYan();
        mingYan.setId(10);
        mingYan.setAuthor("Author1");
        mingYan.setContent("Content1");
        mingYan.setCategoryId(10);
        return mingYan;
    }

    public static  String createJson(String type) throws Exception{
        WebResponse response = WebResponse.getOKResponse("Create " + type +" Finish");
        return objToJson(response);
    }

    public static  String updateJson(String type) throws Exception{
        WebResponse response = WebResponse.getOKResponse("Update " + type +" Finish");
        return objToJson(response);
    }

    public static  String deleteJson(String type) throws Exception{
        WebResponse response = WebResponse.getOKResponse("Remove " + type +" Finish");
        return objToJson(response);
    }

    public static  String okJson(Object obj) throws Exception{
        WebResponse response = WebResponse.getOKResponse(obj);
        return objToJson(response);
    }

    public static  String errorJson(String message) throws Exception{
        WebResponse response = WebResponse.getErrorResponse(message);
        return objToJson(response);
    }

    public static String objToJson(WebResponse response) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response);
        return json;
    }
}
