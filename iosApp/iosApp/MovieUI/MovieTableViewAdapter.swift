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

class MovieTableViewAdapter: NSObject, TableViewAdapterDelegate, MovieCellDelegate {
    typealias Item = Movie
    
    
    weak private var delegate: MovieTableViewAdapterDelegate?
    private var movies: [Movie] = []
    
    init(with delegate: MovieTableViewAdapterDelegate) {
        self.delegate = delegate
    }
    
    func bind(items: [Movie]) {
        self.movies = items
    }
 
    //MARK: - Datasource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MovieCell", for: indexPath)
        
        if let movieCell = cell as? MovieCell {
            movieCell.delegate = self
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
    
    //MARK: - MovieCellDelegate
    func movieCell(_ cell: MovieCell, toggleScrapOf movie: Movie) {
        delegate?.movieTableViewAdapter(self, toggleScrapOf: movie)
    }
    
}

protocol MovieTableViewAdapterDelegate: class {
    func movieTableViewAdapter(_ adapter: MovieTableViewAdapter, toggleScrapOf movie: Movie)
}
