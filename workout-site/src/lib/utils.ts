import { type ClassValue, clsx } from "clsx"
import { twMerge } from "tailwind-merge"

export const api_url = "http://localhost:5202"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}