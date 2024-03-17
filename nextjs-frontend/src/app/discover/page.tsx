"use client";

import { getRestaurants } from "@/api/restaurant";
import RestaurantListItem from "@/components/RestaurantListItem";
import { useQuery } from "@tanstack/react-query";
import { useRouter } from "next/navigation";
import React from "react";

export default function HomePage() {
  const router = useRouter();
  const name = localStorage.getItem("name");

  React.useEffect(() => {
    if (!name) {
      router.push("/register");
    }
  }, []);

  const {
    data: restaurants,
    isLoading,
    isError,
    error,
  } = useQuery({
    queryKey: ["restaurants"],
    queryFn: getRestaurants,
  });

  if (isLoading) {
    return;
  }

  return (
    <div className="flex flex-col justify-center gap-2 px-44 pt-12">
      <div className="mb-5 flex flex-col gap-4">
        <h1 className=" text-6xl font-bold text-gray-800 dark:text-gray-100">
          Discover all
        </h1>
        <p className="text-2xl font-normal text-gray-500 dark:text-gray-100">
          Find out all the restaurants waiting to be discovered!
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
