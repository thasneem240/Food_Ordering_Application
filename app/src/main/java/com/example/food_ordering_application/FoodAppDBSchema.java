package com.example.food_ordering_application;

public class FoodAppDBSchema
{
    public static class FoodDataTable
    {
        public static final String NAME = "FoodData";
        public static class Cols
        {
            public static final String FOOD_NAME = "foodName";
            public static final String FOOD_IMAGE_ID = "foodImageId";
            public static final String FOOD_PRICE = "foodPrice";
            public static final String REST_NAME = "rest_Name";
        }
    }


    public static class RestDataTable
    {
        public static final String NAME = "RestData";
        public static class Cols
        {
            public static final String Rest_NAME = "restName";
            public static final String Rest_IMAGE_ID = "restImageId";
        }
    }


    public static class UserDataTable
    {
        public static final String NAME = "UserData";
        public static class Cols
        {
            public static final String USER_EMAIL = "userEmail";
            public static final String USER_NAME = "userName";
            public static final String USER_PASSWORD = "userPassword";
        }
    }
}
