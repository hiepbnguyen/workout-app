import { useContext, createContext, useState, useEffect } from "react";
import axios from "axios";

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
      "http://localhost:8080/api/v1/user/secured", {
        withCredentials: true
      }
    ).then(response => {
      if (response.status=== 403) {
        setIsAuthenticated(false)
      } else {
        setIsAuthenticated(true)
      }
    }).catch(e => {
      console.log("Error while trying to prove authentication: " + e)
      setIsAuthenticated(false)
    })
  }, [])
  
  return <AuthContext.Provider value={{isAuthenticated, setIsAuthenticated}}>
    {children}
  </AuthContext.Provider>
}