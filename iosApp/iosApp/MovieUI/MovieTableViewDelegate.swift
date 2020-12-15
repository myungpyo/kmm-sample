//
//  MovieTableViewDelegate.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/08.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared

class MovieTableViewAdapter: NSObject, UITableViewDataSource, UITableViewDelegate {
    
    weak private var delegate: MovieCellDelegate?
    private var movies: [MovieEntity] = []
    
    init(with delegate: MovieCellDelegate) {
        self.delegate = delegate
    }
    
    func bind(movies: [MovieEntity]) {
        self.movies = movies
    }
 
    //MARK: - Datasource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MovieCell", for: indexPath)
        
        if let movieCell = cell as? MovieCell {
            movieCell.delegate = self.delegate
            movieCell.movie = self.movies[indexPath.row]
        }
        
        return cell
    }
    
    //MARK: - View
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 120
    }
    
    func scrollViewWillBeginDragging(_ scrollView: UIScrollView) {
//        self.textField.resignFirstResponder()
    }
    
}
