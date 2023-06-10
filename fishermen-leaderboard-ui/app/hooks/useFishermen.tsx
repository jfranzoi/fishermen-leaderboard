import { useEffect, useState } from "react";

const baseURL = 'http://localhost:8080';

export type Fisherman = {
    id: string;
    name: string;
    amount: number;
}

export const useFishermen = () => {
    const [data, setData] = useState<Fisherman[] | undefined>(undefined)
    const [isLoading, setLoading] = useState(false)

    useEffect(() => {
        setLoading(true)
        fetch(`${baseURL}/fishermen`)
            .then((res) => res.json())
            .then((data) => {
                setData(data)
                setLoading(false)
            })
    }, []);

    return [data, isLoading] as const;
};