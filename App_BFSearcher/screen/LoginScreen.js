import { StatusBar } from "expo-status-bar";
import { Button, StyleSheet, Text, View } from "react-native";
import uuid from "react-native-uuid";
import React, { useEffect, useRef, useState } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import * as Update from "expo-updates";
export default function Login() {
  const [id, setId] = useState("");

  const storeData = async (value) => {
    console.log("store uid : " + value);
    setId(value);
    await AsyncStorage.setItem("meme", value);
  };

  useEffect(() => {
    const getData = async () => {
      const value = await AsyncStorage.getItem("meme");
      if (value !== null) {
        setId(value);
      }
    };
    getData();
  }, []);
  const logout = async () => {
    console.log("logout id : " + id);
    try {
      AsyncStorage.clear();
      await Update.reloadAsync();
    } catch (error) {
      console.log(error);
    }
  };
  const handleClcik = async () => {
    if (id == "") {
      storeData(uuid.v4());
    }
    console.log("---> Device ID : " + id);
  };
  return (
    <View style={styles.container}>
      <Text>소프트웨어 공학 10조</Text>
      <Text style={styles.id}>
        {id ? id : "아이디가 없습니다. 아래 버튼을 눌러 아이디를 확인해보세요"}
      </Text>
      <Button title="Check your ID" onPress={handleClcik}></Button>
      <Button title="logout" onPress={logout}></Button>
      <StatusBar style="auto" />
    </View>
  );
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  id: {
    fontSize: 16,
    fontWeight: "bold",
    color: "blue",
    paddingBottom: 20,
  },
});
