import { useEffect, useState } from 'react';
import { Fisherman } from '../models/Fisherman';

const baseURL = 'http://localhost:8080';

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
            .catch((error) => {
                console.log('Could not collect fishermen, error: ', error)
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
            fetch(`${baseURL}/fishermen/${id}?size=10`)
                .then((res) => res.json())
                .then((data) => {
                    setResult(data)
                    setLoading(false)
                })
        }
    }, [id]);

    return [result, isLoading] as const;
};