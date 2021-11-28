import { AxiosInstance } from "axios";
import { http } from "./index";

interface loginDto {
  userId: string;
  userPassword: string;
}

interface signupDto {
  userId: string;
  userPassword: string;
  userName: string;
}

interface userDto {
  id: number;
  name: string;
}

const login = async (userInfo: loginDto): Promise<userDto> => {
  const { data } = await http.post("/login", userInfo);
  return data;
};

const signup = async (userInfo: signupDto): Promise<userDto> => {
  const { data } = await http.post("/join", userInfo);
  return data;
};

const logout = (): Promise<AxiosInstance> => {
  return http.post("/logout");
};

export { login, signup, logout };
