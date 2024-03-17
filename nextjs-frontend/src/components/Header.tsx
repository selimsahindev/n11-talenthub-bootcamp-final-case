"use client";

import React from "react";
import { Button } from "./ui/button";
import { usePathname, useRouter } from "next/navigation";

const Header = () => {
  const pathname = usePathname();
  const router = useRouter();

  const name = localStorage.getItem("name");

  return (
    <div className="flex h-16 w-full items-center justify-between bg-purple-500 px-5">
      <div className="flex flex-row items-center gap-8">
        <h2 className="cursor-default text-2xl font-bold text-yellow-200">
          Restaurants
        </h2>
        {pathname !== "/register" && (
          <div className="flex flex-row items-center gap-2">
            <Button
              variant={pathname === "/" ? "default" : "outline"}
              onClick={() => router.push("/")}
              disabled={pathname === "/"}
            >
              Near to me
            </Button>
            <Button
              variant={pathname === "/discover" ? "default" : "outline"}
              onClick={() => router.push("/discover")}
              disabled={pathname === "/discover"}
            >
              Discover All
            </Button>
          </div>
        )}
      </div>
      <div className="flex flex-row items-center gap-2">
        {name && (
          <Button
            variant="link"
            onClick={() => {
              localStorage.removeItem("name");
              router.push("/register");
            }}
            className="cursor-pointer text-lg text-yellow-200"
          >
            Logout
          </Button>
        )}
      </div>
    </div>
  );
};

export default Header;
