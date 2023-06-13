import { parseISO, format } from 'date-fns';

declare type Props = {
    text: string
}

export default function Date({ text }: Props) {
    const date = parseISO(text)
    return <div>{format(date, 'LLLL d, yyyy')}</div>
}