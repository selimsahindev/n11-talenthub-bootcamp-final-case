"use client";

import { MapPin, Star } from "lucide-react";
import React, { FC } from "react";
import { Button } from "./ui/button";
import { useRouter } from "next/navigation";

interface RestaurantListItemProps extends React.HTMLAttributes<HTMLDivElement> {
  id: string;
  name: string;
  averageRating: number;
  location: {
    latitude: number;
    longitude: number;
  };
}

const RestaurantListItem: FC<RestaurantListItemProps> = ({
  id,
  name,
  averageRating,
  location,
  className,
  ...props
}) => {
  // average rating is float 0-5 and we need to convert it to 0-5
  const router = useRouter();

  return (
    <div
      className={`flex h-80 w-full flex-row items-center justify-start  gap-8 rounded-lg  border p-5 shadow  ${className}`}
      {...props}
    >
      <div className="flex w-52 items-center justify-center">
        <img
          src="https://picsum.photos/id/237/200/300"
          alt="restaurant"
          width={150}
          height={100}
        />
      </div>
      <div className="flex h-[200px] w-1/2 flex-col items-start justify-start gap-2">
        <h2 className="mb-2 text-4xl font-bold text-gray-800 dark:text-gray-100">
          {name}
        </h2>
        <div className="flex flex-row items-center gap-2 text-gray-600">
          <Star className="h-5 w-5 text-gray-600" />
          <p>Rating :</p>
          <Stars rating={averageRating} />
        </div>
        <Button
          className="m-0 flex flex-row gap-1 p-0 text-gray-600 dark:text-gray-100"
          variant={"link"}
          onClick={() =>
            router.push(
              "https://www.google.com/maps/place/" +
                location.latitude +
                "," +
                location.longitude,
            )
          }
        >
          <MapPin className="h-5 w-5 text-gray-600" />
          <p>
            Location: {location.latitude.toFixed(4)},{" "}
            {location.longitude.toFixed(4)}
          </p>
        </Button>
      </div>
    </div>
  );
};
export default RestaurantListItem;

//www.google.com/maps/place/41%C2%B002'58.9%22N+28%C2%B046'17.6%22E/@41.0496944,28.7715556,18z/data=!3m1!4b1!4m4!3m3!8m2!3d41.0496944!4d28.7715556?entry=ttu

const Stars = ({ rating }: { rating: number }) => {
  let roundedRating = Math.round(rating);

  return (
    <div className="flex flex-row">
      {[...Array(5)].map((_, i) => (
        <Star
          key={i}
          className="h-6 w-6"
          color={i < roundedRating ? "rgb(253 224 71)" : "rgb(209 213 219)"}
          fill={i < roundedRating ? "rgb(253 224 71)" : "rgb(209 213 219)"}
        />
      ))}
      <p className="ml-2">({rating})</p>
    </div>
  );
};
