import { http } from "./index";

interface friendDto {
  id: number;
  name: string;
}

const fetchFriends = async (id: number): Promise<friendDto> => {
  const { data } = await http.get(`/friends/login/${id}`);
  return data;
};

export { fetchFriends };
