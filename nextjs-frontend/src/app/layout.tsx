"use client";

import "@/styles/globals.css";

import { Inter } from "next/font/google";
import Header from "@/components/Header";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Toaster } from "@/components/ui/toaster";
import { usePathname, useRouter } from "next/navigation";
import React from "react";

const inter = Inter({
  subsets: ["latin"],
  variable: "--font-sans",
});

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const queryClient = new QueryClient();

  const router = useRouter();
  const pathname = usePathname();
  const name = localStorage.getItem("name");

  React.useEffect(() => {
    if (pathname === "/register") return;

    if (!name) {
      router.push("/register");
    }
  }, [router, pathname, name]);

  return (
    <html lang="en">
      <body className={`font-sans ${inter.variable}`}>
        <QueryClientProvider client={queryClient}>
          <Header />
          {children}
        </QueryClientProvider>
        <Toaster />
      </body>
    </html>
  );
}
