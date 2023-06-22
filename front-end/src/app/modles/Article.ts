import { Comment } from "./Comment";
import { User } from "./User";

export class Article {
    username: string;
    artID: number;
    title: string;
    body: string;
    createdAt: string;
    likes: number;
    dislikes: number;
    disable: boolean;
    comments: Comment[];

    constructor(){
        this.username = '';
        this.artID = NaN;
        this.title = '';
        this.body = '';
        this.createdAt = '';
        this.likes = 0;
        this.dislikes = 0;
        this.disable = false;
        this.comments = [];
    }
}