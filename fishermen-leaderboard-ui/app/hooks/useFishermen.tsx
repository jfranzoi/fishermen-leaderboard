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
    const [page, setPage] = useState(0);

    function loadNext() {
        setLoading(true)
        fetch(`${baseURL}/fishermen?size=5&page=${page + 1}`)
            .then((res) => res.json())
            .then((data) => {
                setPage(page + 1)
                setResults(results.concat(data))
                setLoading(false)
            })
    }

    useEffect(loadNext, []);

    return [results, isLoading, loadNext] as const;
};