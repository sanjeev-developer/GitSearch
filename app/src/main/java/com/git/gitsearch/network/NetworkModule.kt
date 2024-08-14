package com.git.gitsearch.network

import com.git.gitsearch.utility.Routes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Routes.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}

//1. @Module
//Explanation:
//This annotation tells Dagger Hilt that this class is a module that provides dependencies.
//A module in Dagger is a class where you define how to create instances of certain types that can be injected elsewhere in your app.
//2. @InstallIn(SingletonComponent::class)
//Explanation:
//This annotation specifies that the dependencies provided by this module are available throughout the entire application lifecycle (singleton scope).
//SingletonComponent is the component that lives as long as the application, meaning any dependencies provided here are shared across the whole app.
//3. object NetworkModule
//Explanation:
//This is a Kotlin object, meaning it's a singleton by design. It will only be instantiated once, which is fitting since it's used to provide singleton dependencies.
//4. @Provides
//Explanation:
//This annotation is used to tell Hilt how to create and provide instances of dependencies.
//Each @Provides function defines how to create an instance of a specific type.
//5. @Singleton
//Explanation:
//This annotation is used to indicate that the provided instance should be a singleton. This means that the same instance will be used whenever this dependency is injected.
//It's crucial for dependencies like Retrofit which should be reused to avoid unnecessary object creation.
//6. provideRetrofit() function
//Explanation:
//This function creates and provides a Retrofit instance.
//Retrofit.Builder() is used to build the Retrofit instance.
//addConverterFactory(GsonConverterFactory.create()) adds a converter to handle JSON data. This particular converter uses Gson to serialize and deserialize JSON objects.
//baseUrl(Routes.BASE_URL) sets the base URL for the API calls. Routes.BASE_URL should be a constant that holds the base URL string.
//build() creates the Retrofit instance.
//7. providesApiService(retrofit: Retrofit) function
//Explanation:
//This function takes the Retrofit instance provided by provideRetrofit() and uses it to create an implementation of the ApiService interface.
//retrofit.create(ApiService::class.java) tells Retrofit to create a concrete implementation of the ApiService interface, which will be used to make network requests.
//Summary:
//NetworkModule is a Hilt module that provides instances of Retrofit and ApiService for dependency injection across your app.
//provideRetrofit() creates and configures a Retrofit instance.
//providesApiService(retrofit: Retrofit) uses the Retrofit instance to create and provide an ApiService, which can be injected into classes that need to make network requests.