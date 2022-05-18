package com.native_model_integration_attempt;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.native_model_integration_attempt.R;
import com.native_model_integration_attempt.ml.QaAnswer;
import com.native_model_integration_attempt.ml.QaClient;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

import java.util.List;

public class QAModel extends ReactContextBaseJavaModule {

    @Override
    public String getName() {
        return "QAModel";
    }

    public QAModel(ReactApplicationContext context) {
        super(context);
    }

    public QaClient qaClient = new QaClient(getReactApplicationContext());

    @ReactMethod
    public void runModel() {
        qaClient.loadModel();
        Log.d("FROM ANDROID", "Model Loaded");
    }

    @ReactMethod
    private void answerQuestion(String content, Promise promise){
        Log.d("FROM ANDROID ANSWER Q", "Function Begin");
        content = content.trim();
        if (content.isEmpty()) {
            promise.resolve("Empty question");
        }

//        final String questionToAsk = content;

        Log.d("FROM ANDROID ANSWER Q", "WILL START TO PREDICT NOW");
        final List<com.native_model_integration_attempt.ml.QaAnswer> answers = qaClient.predict(content);

        Log.d("FROM ANDROID ANSWER Q", "PREDICTED");

        promise.resolve("Beat Me!");

//        if (!answers.isEmpty()) {
//
//            Log.d("FROM ANDROID ANSWER Q", "ANSWER NOT EMPTY");
//            // Get the top answer
//            QaAnswer topAnswer = answers.get(0);
//            // Show the answer.
//            Log.d("FROM ANDROID ANSWER Q", topAnswer.text);
//            promise.resolve(topAnswer.text);
//        }

//        promise.resolve("Beat Me!");
    }
}
