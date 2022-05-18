import { StyleSheet, Text, TextInput, View, Button } from 'react-native'
import React, {useState, useEffect} from 'react'
import QAModel from './QAModel';

const App = () => {

  const [disabled, setDisabled] = useState(true);
  const [textEntered, setTextEntered] = useState("My name is Wolfgang and I live in Berlin");
  // const [questionEntered, setQuestionEntered] = useState("What is my name?");
  const [tvAnswer, setTvAnswer] = useState("Result shows up here");

  const sentimentAnalyze = async () => {
    // var result = await QAModel.answerQuestion(textEntered, questionEntered);
    var result = await QAModel.answerQuestion(textEntered);
    console.log(result);
    setTvAnswer(result);
  }

  useEffect(() => {
    // if(textEntered.length > 0 && questionEntered.length > 0){
    if(textEntered.length > 0){
      setDisabled(false);
      setTvAnswer("Result shows up here");
    }else{
      setDisabled(true);
    }
  }, [textEntered])

  useEffect(() => {
    const loadTheModel = async () => {
      await QAModel.runModel();
    }
    loadTheModel();
  }, [])

  return (
    <View style={styles.container}>
      <TextInput id="editTextText" style={styles.textInput} onChangeText={setTextEntered} multiline={true} value={textEntered} placeholder="Enter text here..."/>
      <Button title="Sentiment Analyze" id="btnAnswer" multiline={true} style={styles.button} onPress={sentimentAnalyze} disabled={disabled} />
      <Text id="tvAnswer" style={styles.textView}>{tvAnswer}</Text>
    </View>
  )
}

export default App

const styles = StyleSheet.create({
  container:{
    display:"flex",
    flexDirection:"column",
    justifyContent: "space-evenly",
    height: "100%",
    alignItems: "center"
  },
})