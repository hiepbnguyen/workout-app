import { Card } from "../ui/card";
import { Timer } from "lucide-react";

export default function ActiveMinutes() {
  const activeMinutes = 5;
  return(
    <Card className="p-5 gap-5 flex items-center">
      <Timer size={58}/>
      <div>
        <div className="text-sm text-gray-300">
          This Week's Activity
        </div>
        <div className="text-4xl font-bold pr-10">
          {activeMinutes} Minutes
        </div>
      </div>
    </Card>
  )
}