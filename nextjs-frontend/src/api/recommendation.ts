import axiosInstance from "./";

interface IRestaurantRequest {
  latitude: string;
  longitude: string;
}

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

export const getRecommendations = async (data: IRestaurantRequest) => {
  const response = await axiosInstance.get(
    `/recommendation-service/api/v1/search/restaurants/by-location-near?location=${data.latitude},${data.longitude}`,
  );
  return response.data as IRestaurantResponse;
};
