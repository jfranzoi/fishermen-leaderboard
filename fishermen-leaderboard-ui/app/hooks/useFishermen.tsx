import { useEffect, useState } from 'react';

const baseURL = 'http://localhost:8080';

export type Fisherman = {
    id: string;
    name: string;
    amount: number;
    picture: string;
    recollections: Recollection[];
}

export type Recollection = {
    amount: number;
    date: Date;
    picture: string;
}

export const useFishermen = () => {
    const [results, setResults] = useState<Fisherman[]>([])
    const [isLoading, setLoading] = useState(false)
    const [page, setPage] = useState(0);

    function loadNext() {
        setLoading(true)
        fetch(`${baseURL}/fishermen?days=30&size=5&page=${page + 1}`)
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

export const useFishermanDetail = (id: string) => {
    const [result, setResult] = useState<Fisherman | undefined>(undefined)
    const [isLoading, setLoading] = useState(false)

    useEffect(() => {
        if (id) {
            setLoading(true)
            fetch(`${baseURL}/fishermen/${id}`)
                .then((res) => res.json())
                .then((data) => {
                    setResult(data)
                    setLoading(false)
                })
        }
    }, [id]);

    return [result, isLoading] as const;
};