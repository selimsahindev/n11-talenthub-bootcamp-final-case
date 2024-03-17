"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { useMutation } from "@tanstack/react-query";
import { createUser } from "@/api/register";
import { useRouter } from "next/navigation";
import { Loader2 } from "lucide-react";
import { useToast } from "@/components/ui/use-toast";

const userFormSchema = z.object({
  name: z.string().min(3, "Name is too short").max(255, "Name is too long"),
  surname: z
    .string()
    .min(3, "Surname is too short")
    .max(255, "Surname is too long"),
  email: z.string().email("Invalid email"),
  longitude: z.string(),
  latitude: z.string(),
});

export type UserFormValues = z.infer<typeof userFormSchema>;

export default function Login() {
  const router = useRouter();
  const { toast } = useToast();

  const form = useForm<UserFormValues>({
    resolver: zodResolver(userFormSchema),
    defaultValues: {
      name: "",
      surname: "",
      email: "",
      longitude: "32.85794341887077",
      latitude: "39.90180157645193",
    },
  });

  const { mutateAsync: createUserMutation, isPending } = useMutation({
    mutationFn: createUser,
    onError: (error) => {
      console.log(error);
      toast({
        title: "Error",
        // @ts-expect-error
        // eslint-disable-next-line @typescript-eslint/no-unsafe-member-access
        description: error.response.data.data.message as string,
        variant: "destructive",
      });
    },
  });

  const onSubmit = async (data: UserFormValues) => {
    await createUserMutation({
      name: data.name,
      surname: data.surname,
      email: data.email,
      location: {
        latitude: parseFloat(data.latitude),
        longitude: parseFloat(data.longitude),
      },
    });

    // put these values in local storage
    localStorage.setItem("name", data.name);
    localStorage.setItem("surname", data.surname);
    localStorage.setItem("email", data.email);
    localStorage.setItem("latitude", data.latitude);
    localStorage.setItem("longitude", data.longitude);

    router.push("/");
  };

  return (
    <div className="flex min-h-fit flex-col items-center justify-center gap-5 pb-10 pt-20">
      <h1 className="mb-12 text-6xl font-bold text-gray-800 dark:text-gray-100">
        Register
      </h1>
      <Form {...form}>
        <form
          onSubmit={form.handleSubmit(onSubmit)}
          className="w-[28rem] space-y-2 border p-5 shadow"
        >
          <FormField
            control={form.control}
            name="name"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Name</FormLabel>
                <FormControl>
                  <Input placeholder="Give name" {...field} />
                </FormControl>
                <FormDescription>
                  Your name must be between 3 and 255 characters long.
                </FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="surname"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Surname</FormLabel>
                <FormControl>
                  <Input placeholder="Give surname" {...field} />
                </FormControl>
                <FormDescription>
                  Your surname must be between 3 and 255 characters long.
                </FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="email"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Email</FormLabel>
                <FormControl>
                  <Input placeholder="Give email" {...field} />
                </FormControl>
                <FormDescription>Your email must be valid.</FormDescription>
                <FormMessage />
              </FormItem>
            )}
          />
          <div className="flex flex-row gap-2">
            <FormField
              control={form.control}
              name="latitude"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Latitude</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormDescription></FormDescription>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="longitude"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Longitude</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormDescription></FormDescription>
                  <FormMessage />
                </FormItem>
              )}
            />
          </div>

          <Button type="submit">
            {isPending ? (
              <>
                <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                Please Wait
              </>
            ) : (
              <h3 className="font-sans text-base font-semibold text-white">
                Register
              </h3>
            )}
          </Button>
        </form>
      </Form>
    </div>
  );
}
