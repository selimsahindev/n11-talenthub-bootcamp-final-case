import axiosInstance from "./";

export interface IRestaurant {
  id: string;
  name: string;
  averageRating: number;
  location: {
    latitude: number;
    longitude: number;
  };
}

interface IRestaurantResponse {
  data: IRestaurant[];
  message: string;
  responseDate: string;
}

export const getRestaurants = async () => {
  const response = await axiosInstance.get(
    "/restaurant-service/api/v1/restaurants",
  );
  return response.data as IRestaurantResponse;
};
