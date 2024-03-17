"use client";

import { env } from "@/env";
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: env.NEXT_PUBLIC_BACKEND_URL,
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
    "ngrok-skip-browser-warning": "69420",
  },
  withCredentials: true,
});

export default axiosInstance;
