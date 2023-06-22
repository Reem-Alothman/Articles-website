import { User } from "./User";

export class Comment{
    username: string;
    cmtID: number;
    createdAt: Date;
    comment: String;
    author: User

    constructor(){
        this.username = '',
        this.cmtID = NaN;
        this.createdAt = new Date;
        this.comment = '';
        this.author = new User;
    }
}