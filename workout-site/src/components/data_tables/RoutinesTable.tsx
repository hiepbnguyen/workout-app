import { getListOfRoutines } from "@/services/Api";
import { useEffect } from "react";

export default function RoutinesTable() {
    useEffect(() => {
        getListOfRoutines()
    }, [])

    return (
        <>
        </>
    )
}