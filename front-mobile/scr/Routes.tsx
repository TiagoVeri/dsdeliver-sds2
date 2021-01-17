import React from 'react';
import { NavigationContainer } from "@react-navigation/native";
import {createStackNavigator } from "@react-navigation/stack";
import Home from "./Home";
import Orders from "./Orders";
//pilha de navegação
const Stack = createStackNavigator();
function Routes(){
    return(
        //emcapsula todas as rodas da aplicação
        <NavigationContainer>
            <Stack.Navigator
                headerMode="none"
                screenOptions={{
                    //passa estilo para todas as telas
                    cardStyle: {
                        backgroundColor: '#FFF'
                    }
                }}
            >   
                <Stack.Screen name="Home" component={Home}></Stack.Screen>
                <Stack.Screen name="Orders" component={Orders}></Stack.Screen>
            </Stack.Navigator>
        </NavigationContainer>
    )
}

export default Routes;