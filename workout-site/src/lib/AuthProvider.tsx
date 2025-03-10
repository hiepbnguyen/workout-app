import { useContext, createContext, useState, useEffect } from "react";
import axios from "axios";
import { api_url } from "./utils";

type AuthProviderState = {
  isAuthenticated: boolean | null
  setIsAuthenticated: (isAuthenticated: boolean | null) => void
}

const initialState = {
  isAuthenticated: false,
  setIsAuthenticated: () => null
}

const AuthContext = createContext<AuthProviderState>(initialState)

export function useAuth() {
  return useContext(AuthContext)
}

export default function AuthProvider({children}: {children: any}) {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null)

  useEffect(() => {
    axios.get(
      api_url+"/api/auth/protected", {
        withCredentials: true
      }
    ).then(response => {
      if (response.status === 403) {
        setIsAuthenticated(false)
      } else {
        setIsAuthenticated(true)
      }
    }).catch(e => {
      if (e.code == axios.AxiosError.ERR_NETWORK) {
        console.error("Cannot connect to server")
      }

      console.log("Error while trying to prove authentication: " + e)
      setIsAuthenticated(false)
    })
  }, [])
  
  return <AuthContext.Provider value={{isAuthenticated, setIsAuthenticated}}>
    {children}
  </AuthContext.Provider>
}