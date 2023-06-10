import { useEffect, useState } from "react";

const baseURL = 'http://localhost:8080';

export type Fisherman = {
    id: string;
    name: string;
    amount: number;
}

export const useFishermen = () => {
    const [results, setResults] = useState<Fisherman[]>([])
    const [isLoading, setLoading] = useState(false)

    function loadNext() {
        setLoading(true)
        fetch(`${baseURL}/fishermen?from=${results.length}`)
            .then((res) => res.json())
            .then((data) => {
                setResults(results.concat(data))
                setLoading(false)
            })
    }

    useEffect(loadNext, []);

    return [results, isLoading, loadNext] as const;
};