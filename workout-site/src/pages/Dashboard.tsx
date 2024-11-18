import { Button } from "@/components/ui/button"
import { useNavigate } from "react-router-dom"

export default function Dashboard ({ username } : { username: string }) {
    const history = useNavigate()

    const handleLogout = () => {
        history('/login')
    }

    return (
        <div>
            <h1>
                Hello, {username}
            </h1>
            <Button onClick={handleLogout}>
                Logout
            </Button>
        </div>
    )
}