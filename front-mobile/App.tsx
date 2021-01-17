import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Header from './scr/Header';
import { useFonts, OpenSans_400Regular, OpenSans_700Bold } from '@expo-google-fonts/open-sans';
import AppLoading from 'expo-app-loading';
import Home from './scr/Home';

export default function App() {
 
  //instalação da google font
  let [fontsLoaded] = useFonts({
    OpenSans_400Regular,
    OpenSans_700Bold,
  });

  if (!fontsLoaded) {
    return <AppLoading />;
  }
  // end
  return (
    <View style={styles.container}>
      <StatusBar style="auto" />
      <Header/>
      <Home />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1
  },
});
