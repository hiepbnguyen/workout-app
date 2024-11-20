import { type ClassValue, clsx } from "clsx"
import { twMerge } from "tailwind-merge"

export const api_start = "http://localhost:8080/api/v1/user"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}