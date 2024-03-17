"use client";

import { getRecommendations } from "@/api/recommendation";
import RestaurantListItem from "@/components/RestaurantListItem";
import { useQuery } from "@tanstack/react-query";

export default function HomePage() {
  const langtiude = localStorage.getItem("longitude");
  const latitude = localStorage.getItem("latitude");

  const {
    data: restaurants,
    isLoading,
    isError,
    error,
  } = useQuery({
    queryKey: ["recommendations"],
    queryFn: () =>
      getRecommendations({
        latitude: latitude!,
        longitude: langtiude!,
      }),
  });

  if (isLoading) return <div>Loading...</div>;
  // if (isError) return <div>Error: {error}</div>;

  return (
    <div className="flex flex-col justify-center gap-2 px-32 pt-12">
      <div className="mb-5 flex flex-col gap-2">
        <h1 className=" text-6xl font-bold text-gray-800 dark:text-gray-100">
          Restaurants
        </h1>
        <p className="text-2xl font-normal text-gray-500 dark:text-gray-100">
          You can find the best restaurants near you.{" "}
        </p>
      </div>
      <div className="flex flex-col items-start justify-center gap-4">
        {restaurants?.data.map((restaurant) => (
          <RestaurantListItem
            key={restaurant.id}
            id={restaurant.id}
            name={restaurant.name}
            averageRating={restaurant.averageRating}
            location={restaurant.location}
          />
        ))}
      </div>
    </div>
  );
}
