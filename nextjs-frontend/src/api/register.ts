import axiosInstance from "./";

export interface ICreateUserRequest {
  name: string;
  surname: string;
  email: string;
  location: {
    latitude: number;
    longitude: number;
  };
}

export interface ICreateUserResponse {
  data: {
    id: string;
    name: string;
    averageRating: number;
    location: {
      latitude: number;
      longitude: number;
    };
  };
  message: string;
  responseDate: string;
}

export const createUser = async (data: ICreateUserRequest) => {
  const response = await axiosInstance.post<
    ICreateUserRequest,
    ICreateUserResponse
  >("/user-service/api/v1/users", {
    status: "ACTIVE",
    ...data,
  });
  return response.data;
};
