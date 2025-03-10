import { Routes, Route } from 'react-router-dom'
import Login from './pages/Login'
import AuthProvider from './lib/AuthProvider'
import Dashboard from './pages/Dashboard'
import { ThemeProvider } from './components/ThemeProvider'
import Workouts from './pages/Workouts'

function App() {
  return (
    <AuthProvider>
      <ThemeProvider defaultTheme='dark' storageKey='vite-ui-theme'>
        <Routes>
          <Route path="/" element={<Dashboard />}/>
          <Route path="/workouts" element={<Workouts />}/>
          <Route path="/login" element={<Login />}/>
        </Routes>
      </ThemeProvider>
    </AuthProvider>
  )
}

export default App
